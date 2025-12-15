package hu.utils;

import org.springframework.stereotype.Component;

@Component
public class ReaderHolder {
    ThreadLocal<Object> readerThreadLocal = new ThreadLocal<>();
    public void setReader(Object reader){
        readerThreadLocal.set(reader);
    }
    public Object getReader(){
        return readerThreadLocal.get();
    }
    public void removeReader(){
        readerThreadLocal.remove();
    }
}
