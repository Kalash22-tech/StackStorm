Practical 02: fork() System Call

Aim:To write a program to demonstrate the fork() system call in Linux.

Software Required:GCC Compiler, Linux Terminal

Code:

#include <stdio.h>
#include <unistd.h>

int main() {
    pid_t pid = fork();

    if (pid < 0) {
        printf("Fork failed.\n");
    } else if (pid == 0) {
        printf("This is the child process. PID: %d\n", getpid());
    } else {
        printf("This is the parent process. PID: %d\n", getpid());
    }
    return 0;
}

Output:

This is the parent process. PID: 12345
This is the child process. PID: 12346

How to Run:

gcc fork_demo.c -o fork_demo
./fork_demo

Conclusion:The fork() system call is used to create a new process by duplicating the current process.

Practical 03: FCFS Scheduling Algorithm

Aim:To write a program to implement FCFS (First Come First Serve) scheduling algorithm.

Software Required:GCC Compiler / C IDE

Code:

#include <stdio.h>

int main() {
    int n, i;
    printf("Enter number of processes: ");
    scanf("%d", &n);

    int bt[n], wt[n], tat[n];

    printf("Enter burst time for each process:\n");
    for(i = 0; i < n; i++) {
        printf("P[%d]: ", i+1);
        scanf("%d", &bt[i]);
    }

    wt[0] = 0;
    for(i = 1; i < n; i++) {
        wt[i] = wt[i-1] + bt[i-1];
    }

    printf("\nProcess\tBT\tWT\tTAT\n");
    for(i = 0; i < n; i++) {
        tat[i] = wt[i] + bt[i];
        printf("P[%d]\t%d\t%d\t%d\n", i+1, bt[i], wt[i], tat[i]);
    }
    return 0;
}

Output:User inputs burst times. Displays a table of BT, WT, TAT.

How to Run:

gcc fcfs.c -o fcfs
./fcfs

Conclusion:FCFS scheduling executes processes in the order they arrive.

Practical 04: SJF Scheduling Algorithm

Aim:To write a program to implement SJF (Shortest Job First) scheduling algorithm.

Software Required:GCC Compiler / C IDE

Code:

#include <stdio.h>

int main() {
    int n, i, j;
    printf("Enter number of processes: ");
    scanf("%d", &n);

    int p[n], bt[n], wt[n], tat[n], temp;
    for(i = 0; i < n; i++) {
        p[i] = i+1;
        printf("Enter burst time for P[%d]: ", i+1);
        scanf("%d", &bt[i]);
    }

    for(i = 0; i < n-1; i++) {
        for(j = i+1; j < n; j++) {
            if(bt[i] > bt[j]) {
                temp = bt[i]; bt[i] = bt[j]; bt[j] = temp;
                temp = p[i]; p[i] = p[j]; p[j] = temp;
            }
        }
    }

    wt[0] = 0;
    for(i = 1; i < n; i++) {
        wt[i] = wt[i-1] + bt[i-1];
    }

    printf("\nProcess\tBT\tWT\tTAT\n");
    for(i = 0; i < n; i++) {
        tat[i] = wt[i] + bt[i];
        printf("P[%d]\t%d\t%d\t%d\n", p[i], bt[i], wt[i], tat[i]);
    }
    return 0;
}

Output:Displays processes sorted by burst time and calculates WT, TAT.

How to Run:

gcc sjf.c -o sjf
./sjf

Conclusion:SJF scheduling improves efficiency by selecting the shortest burst time first.



Here's the full code starting from **Practical 05 to Practical 10**, filled in based on the aims listed in your document:

---

### **Practical 05: Producer Consumer Problem using Semaphore**

```c
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

#define SIZE 5

int buffer[SIZE], in = 0, out = 0;
sem_t empty, full;
pthread_mutex_t mutex;

void* producer(void* arg) {
    int item;
    for (int i = 0; i < SIZE; i++) {
        item = rand() % 100;
        sem_wait(&empty);
        pthread_mutex_lock(&mutex);
        buffer[in] = item;
        printf("Producer produced: %d\n", item);
        in = (in + 1) % SIZE;
        pthread_mutex_unlock(&mutex);
        sem_post(&full);
        sleep(1);
    }
    return NULL;
}

void* consumer(void* arg) {
    for (int i = 0; i < SIZE; i++) {
        sem_wait(&full);
        pthread_mutex_lock(&mutex);
        int item = buffer[out];
        printf("Consumer consumed: %d\n", item);
        out = (out + 1) % SIZE;
        pthread_mutex_unlock(&mutex);
        sem_post(&empty);
        sleep(1);
    }
    return NULL;
}

int main() {
    pthread_t prod, cons;
    sem_init(&empty, 0, SIZE);
    sem_init(&full, 0, 0);
    pthread_mutex_init(&mutex, NULL);

    pthread_create(&prod, NULL, producer, NULL);
    pthread_create(&cons, NULL, consumer, NULL);

    pthread_join(prod, NULL);
    pthread_join(cons, NULL);

    sem_destroy(&empty);
    sem_destroy(&full);
    pthread_mutex_destroy(&mutex);
    return 0;
}
```

**Compile with:**

```bash
gcc producer_consumer.c -o pc -lpthread
./pc
```

---

### **Practical 06: FIFO Page Replacement Algorithm**

```c
#include <stdio.h>

int main() {
    int frames, pages, i, j, k, pageFaults = 0;
    printf("Enter number of frames: ");
    scanf("%d", &frames);
    printf("Enter number of pages: ");
    scanf("%d", &pages);

    int page[pages], temp[frames];
    printf("Enter page reference string:\n");
    for(i = 0; i < pages; i++)
        scanf("%d", &page[i]);

    for(i = 0; i < frames; i++)
        temp[i] = -1;

    j = 0;
    for(i = 0; i < pages; i++) {
        int available = 0;
        for(k = 0; k < frames; k++) {
            if(temp[k] == page[i])
                available = 1;
        }

        if(!available) {
            temp[j] = page[i];
            j = (j + 1) % frames;
            pageFaults++;
        }
    }

    printf("Total Page Faults: %d\n", pageFaults);
    return 0;
}
```

---

### **Practical 07: LRU Page Replacement Algorithm**

```c
#include <stdio.h>

int findLRU(int time[], int n) {
    int i, minimum = time[0], pos = 0;
    for(i = 1; i < n; ++i) {
        if(time[i] < minimum) {
            minimum = time[i];
            pos = i;
        }
    }
    return pos;
}

int main() {
    int frames, pages, counter = 0, faults = 0;
    printf("Enter number of frames: ");
    scanf("%d", &frames);
    printf("Enter number of pages: ");
    scanf("%d", &pages);

    int page[pages], temp[frames], time[frames];
    printf("Enter page reference string:\n");
    for(int i = 0; i < pages; i++)
        scanf("%d", &page[i]);

    for(int i = 0; i < frames; i++)
        temp[i] = -1;

    for(int i = 0; i < pages; i++) {
        int flag = 0;
        for(int j = 0; j < frames; j++) {
            if(temp[j] == page[i]) {
                flag = 1;
                time[j] = ++counter;
                break;
            }
        }

        if(!flag) {
            int pos = -1;
            for(int j = 0; j < frames; j++) {
                if(temp[j] == -1) {
                    pos = j;
                    break;
                }
            }
            if(pos == -1)
                pos = findLRU(time, frames);

            temp[pos] = page[i];
            time[pos] = ++counter;
            faults++;
        }
    }

    printf("Total Page Faults: %d\n", faults);
    return 0;
}
```

---

### **Practical 08: SCAN Disk Scheduling Algorithm**

```c
#include <stdio.h>
#include <stdlib.h>

int main() {
    int i, j, n, total = 0, head, direction;
    printf("Enter number of requests: ");
    scanf("%d", &n);
    int request[n];
    printf("Enter the requests:\n");
    for(i = 0; i < n; i++)
        scanf("%d", &request[i]);
    printf("Enter initial head position: ");
    scanf("%d", &head);
    printf("Enter direction (0 for left, 1 for right): ");
    scanf("%d", &direction);

    request[n] = head;
    n++;
    for(i = 0; i < n; i++) {
        for(j = i+1; j < n; j++) {
            if(request[i] > request[j]) {
                int temp = request[i];
                request[i] = request[j];
                request[j] = temp;
            }
        }
    }

    int index;
    for(i = 0; i < n; i++) {
        if(request[i] == head) {
            index = i;
            break;
        }
    }

    printf("Seek Sequence:\n");
    if(direction == 1) {
        for(i = index; i < n; i++) {
            printf("%d ", request[i]);
            total += abs(head - request[i]);
            head = request[i];
        }
        for(i = index - 1; i >= 0; i--) {
            printf("%d ", request[i]);
            total += abs(head - request[i]);
            head = request[i];
        }
    } else {
        for(i = index; i >= 0; i--) {
            printf("%d ", request[i]);
            total += abs(head - request[i]);
            head = request[i];
        }
        for(i = index + 1; i < n; i++) {
            printf("%d ", request[i]);
            total += abs(head - request[i]);
            head = request[i];
        }
    }
    printf("\nTotal Head Movement: %d\n", total);
    return 0;
}
```

---

### **Practical 09: Priority Scheduling Algorithm**

```c
#include <stdio.h>

int main() {
    int n;
    printf("Enter number of processes: ");
    scanf("%d", &n);
    int bt[n], p[n], prio[n], wt[n], tat[n], i, j;

    for(i = 0; i < n; i++) {
        p[i] = i+1;
        printf("Enter burst time and priority for P[%d]: ", i+1);
        scanf("%d%d", &bt[i], &prio[i]);
    }

    for(i = 0; i < n; i++) {
        for(j = i+1; j < n; j++) {
            if(prio[i] > prio[j]) {
                int temp = prio[i]; prio[i] = prio[j]; prio[j] = temp;
                temp = bt[i]; bt[i] = bt[j]; bt[j] = temp;
                temp = p[i]; p[i] = p[j]; p[j] = temp;
            }
        }
    }

    wt[0] = 0;
    for(i = 1; i < n; i++) {
        wt[i] = wt[i-1] + bt[i-1];
    }

    printf("\nProcess\tPriority\tBT\tWT\tTAT\n");
    for(i = 0; i < n; i++) {
        tat[i] = wt[i] + bt[i];
        printf("P[%d]\t%d\t\t%d\t%d\t%d\n", p[i], prio[i], bt[i], wt[i], tat[i]);
    }
    return 0;
}
```

---

### **Practical 10: Optimal Page Replacement Algorithm**

```c
#include <stdio.h>

int predict(int pages[], int frames[], int n, int index, int frameCount) {
    int res = -1, farthest = index;
    for(int i = 0; i < frameCount; i++) {
        int j;
        for(j = index; j < n; j++) {
            if(frames[i] == pages[j]) {
                if(j > farthest) {
                    farthest = j;
                    res = i;
                }
                break;
            }
        }
        if(j == n)
            return i;
    }
    return (res == -1) ? 0 : res;
}

int main() {
    int n, frameCount;
    printf("Enter number of pages: ");
    scanf("%d", &n);
    int pages[n];
    printf("Enter the pages: ");
    for(int i = 0; i < n; i++)
        scanf("%d", &pages[i]);

    printf("Enter number of frames: ");
    scanf("%d", &frameCount);
    int frames[frameCount];
    int count = 0, pageFaults = 0;

    for(int i = 0; i < frameCount; i++)
        frames[i] = -1;

    for(int i = 0; i < n; i++) {
        int flag = 0;
        for(int j = 0; j < frameCount; j++) {
            if(frames[j] == pages[i]) {
                flag = 1;
                break;
            }
        }

        if(!flag) {
            if(count < frameCount)
                frames[count++] = pages[i];
            else {
                int j = predict(pages, frames, n, i + 1, frameCount);
                frames[j] = pages[i];
            }
            pageFaults++;
        }
    }

    printf("Total Page Faults = %d\n", pageFaults);
    return 0;
}
```

---

Would you like me to insert these into your document now?
