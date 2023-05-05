package etu1792.framework;

public class FileUpload {
    String name;
    String path;
    byte[] bytes;

    public FileUpload(){}
    
    public FileUpload(String name, String path, byte[] bytes) {
        this.name = name;
        this.path = path;
        this.bytes = bytes;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }    
}