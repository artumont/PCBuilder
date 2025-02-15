import fastapi
import logging
import datetime
from .utils import logging_filters

app = fastapi.FastAPI()

# @todo: Add routers

init_time = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

# @todo: Add middleware

stream_handler = logging.StreamHandler()
stream_handler.addFilter(logging_filters.RemovePyExtensionFilter)
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(filename)s.%(funcName)s - %(levelname)s - %(message)s",
    handlers=[stream_handler],
)
logger = logging.getLogger(__name__)