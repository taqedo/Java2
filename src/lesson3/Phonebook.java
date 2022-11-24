package lesson3;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Phonebook extends HashMap
 implements Map, Cloneable, Serializable {
//    переопределяем метод put для добавления номера телефона, если такой пользователь уже есть:
    @Override
    public Object put(Object key, Object value) {
        String pk = (String) key;
        String pv = (String) value;
        if (super.containsKey(pk)){
            return super.put(key, super.get(pk) + "," + pv);
        }
        return super.put(key, value);
    }
//    или добавляем метод add:
    public String add(String key, String value){
        String pk = key;
        String pv = value;
        if (super.containsKey(pk)){
            return (String) super.put(key, super.get(pk) + "," + pv);
        }
        return (String) super.put(key, value);

    }


}
