import asyncio
import socket
import threading
import sys
import os
import time
#from queue import Queue
#from controller import Controller

# Create a thread to monitor the Xbox Controller
# Send updates via IPC to the Java thread
# Display information in the GUI

#class NetworkingThread(object):
#
#    def __init__(self):
#        self.event = threading.Event()
#        self.ws = create_connection("ws://localhost:8080/websocket")
#        self.queue = Queue()
#        self.run(self)

#    def run(self):
#        while True:
#            ws.send("Test data")
#            print("sending")
#            time.sleep(0.1)
#        ws.close()

print ("Connecting...")
client = socket.socket(socket.AF_UNIX, socket.SOCK_DGRAM)
client.connect("/tmp/python_unix_sockets_test")
print("Ready!")
print("Ctrl-C to quit.")
print("Sending 'done' shuts down the server and quits.")
while True:
    try:
        data = input("> ")
        if "" != data:
            print("SEND:", data)
            client.send(bytes(data, 'utf-8'))
            if "done" == data:
                print("Disconnecting.")
                break
    except KeyboardInterrupt:
        print("Disconnecting...")
        client.close()
print("Done")
