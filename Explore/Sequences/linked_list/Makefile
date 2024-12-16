CC = gcc
CFLAGS = -std=c99 -I/opt/homebrew/Cellar/check/0.15.2/include
LDFLAGS = /opt/homebrew/Cellar/check/0.15.2/lib/libcheck.a
SOURCES = test_linked_list.c linked_list.c
TARGET = test_linked_list

all: $(TARGET)

$(TARGET): $(SOURCES)
	$(CC) $(CFLAGS) -o $(TARGET) $(SOURCES) $(LDFLAGS)

clean:
	rm -f $(TARGET)