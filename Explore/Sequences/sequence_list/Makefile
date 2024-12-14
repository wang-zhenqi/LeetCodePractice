CC = gcc
CFLAGS = -std=c99 -I/opt/homebrew/Cellar/check/0.15.2/include
LDFLAGS = /opt/homebrew/Cellar/check/0.15.2/lib/libcheck.a
SOURCES = test_sequence_list.c sequence_list.c
TARGET = test_sequence_list

all: $(TARGET)

$(TARGET): $(SOURCES)
	$(CC) $(CFLAGS) -o $(TARGET) $(SOURCES) $(LDFLAGS)

clean:
	rm -f $(TARGET)