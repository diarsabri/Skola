#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/wait.h>

int main() {

  int fd = open("tmp.txt", O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);  
  int pid = fork();

  if(pid == 0) {

    // printf("new entry %d \n", fd);
    dup2(fd, 1);
    close(fd);
    execl("code", "code", NULL);
  } else {
    wait(NULL);
    //sleep(2);
    //dprintf(fd, "OK!");    
  }
  return 0;
}
