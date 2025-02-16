import fastapi
import logging
import datetime

from .middleware import sockets
from .utils import log_filters

# @description: Import Routes
from .routes.auth import auth_router as auth_routes

app = fastapi.FastAPI(
    title="PCBuilder Frontend REST API",
    description="REST API for the PCBuilder frontend",
    version="0.1",
    openapi_tags=[
        {
            "name": "authentication",
            "description": "Operations related to authentication"
        }
    ],
)

# @description: Add routers to the application
app.include_router(auth_routes.router)

init_time = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

# @todo: Add middleware (e.g., logging, error handling, etc.)

stream_handler = logging.StreamHandler()
stream_handler.addFilter(log_filters.RemovePyExtensionFilter)
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(filename)s.%(funcName)s - %(levelname)s - %(message)s",
    handlers=[stream_handler],
)
logger = logging.getLogger(__name__)

@app.route("/health")
def health_check():
    return 200, {"status": "OK", "up_since": init_time}