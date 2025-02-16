import logging
from fastapi import APIRouter, Depends, Request

logger = logging.getLogger(__name__)
router = APIRouter(
    prefix="/api/v1/auth",
    tags=["authentication"],
    responses={
        404: {"status": "error", "message": "Not found"},
        500: {"status": "error", "message": "Internal server error"}
    },
)

@router.get("/login")
def login_handler(request: Request):
    try:
        req_data = request.json()
    except Exception as e:
        logger.error(f"Error parsing request data: {e}")
        return {"status": "error", "message": "Error parsing request data"}