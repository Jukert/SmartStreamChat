package parser;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConverterJSON<T> {
    private ObjectMapper objectMapper;
    private File setting;
    private final Class<T> typeParameterClass;


    public ConverterJSON(Class<T> typeParameterClass) {
        objectMapper = new ObjectMapper();
        setting = new File(getClass().getResource("/json/settings.json").getPath());
        this.typeParameterClass = typeParameterClass;
    }

    public File getSetting() {
        return setting;
    }

    public void setSetting(File setting) {
        this.setting = setting;
    }

    public void toJSON(T val){
        try {
            objectMapper.writeValue(setting,val);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public T toObject() throws IOException {
        return objectMapper.readValue(setting, typeParameterClass);
    }

}
