import socket
import os, os.path
import time

if os.path.exists("/tmp/python_unix_sockets_test"):
    os.remove("/tmp/python_unix_sockets_test")

print("Opening socket...")
server = socket.socket(socket.AF_UNIX, socket.SOCK_DGRAM)
server.bind("/tmp/python_unix_sockets_test")

print("Listening...")
while True:
    data = server.recv(1024)
    if not data:
        break
    else:
        print("-" * 20)
        print(data.decode("utf-8"))
        if "done" == data:
            break
print("-" * 20)
print("Closing...")
server.close()
os.remove("/tmp/python_unix_sockets_test")
print("Done")
