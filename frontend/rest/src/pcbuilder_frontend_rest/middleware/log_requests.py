import fastapi
import logging

logger = logging.getLogger(__name__)

class OnRequestLoggingMiddleware:
    def __init__(self, app: fastapi.FastAPI):
        self.app = app

    async def __call__(self, request: fastapi.Request):
        logger.info(f"Ip '{request.client.host}' made a '{request.method}' request to '{request.url.path}' endpoint")
        response = await self.app(request)
        return response