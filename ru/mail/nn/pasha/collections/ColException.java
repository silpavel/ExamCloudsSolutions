package ru.mail.nn.pasha.collections;

class ColException{}
class EmptyQueueException extends RuntimeException{
    public EmptyQueueException(String message) {
        super(message);
    }
}
class FullStackTException extends RuntimeException{
    public FullStackTException(String message) {
        super(message);
    }
}
class EmptyStackTException extends RuntimeException{
    public EmptyStackTException(String message) {
        super(message);
    }
}
