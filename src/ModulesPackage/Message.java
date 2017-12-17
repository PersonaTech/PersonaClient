package ModulesPackage;

import java.io.Serializable;

/**
 * Created by benshmuel on 23/11/2017.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 123L;

    private String name;
    private int age;

    public Message() {
    }

    public Message(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
