import logging
from contextlib import suppress
import websockets
from asyncio import Queue
import asyncio

logger = logging.getLogger(__name__)


class ClientlessWebSocketServer(object):
    def __init__(self, port):
        self._active_connections = set()
        self.port = port
        self.server = None
        self.queue = Queue()

    async def start_server(self):
        self.server = await websockets.serve(self.handle_new_connection, '0.0.0.0', self.port)

    async def stop_server(self):
        await self.server.close()
        await self.server.wait_closed()
        self.server = None

    async def handle_new_connection(self, ws, path):
        logger.info("WS connection open")
        self._active_connections.add(ws)
        with suppress(websockets.ConnectionClosed):
            while True:
                result = await ws.recv()
                await self.handle_msg(result)
        logger.info("WS connection close")
        self._active_connections.remove(ws)

    async def handle_msg(self, msg):
        logger.debug("WS enqueue msg")
        await self.queue.put(msg)

    async def recv(self):
        logger.debug("WS wait msg")
        msg = await self.queue.get()
        return msg

    async def send(self, msg):
        # logger.debug("WS seng msg")
        for ws in self._active_connections:
            asyncio.ensure_future(ws.send(msg))
