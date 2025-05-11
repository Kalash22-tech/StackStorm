from collections import deque

queue = deque()

# Enqueue
queue.append(10)
queue.append(20)

# Dequeue
queue.popleft()

# Peek
if queue:
    print("Front Element:", queue[0])
