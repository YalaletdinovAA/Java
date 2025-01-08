package src.homeWorks.HomeWork6.dop;

import java.util.Objects;

public class Channel {
    private String name;
    private int number;
    private String program;

    public Channel(String name, int number, String program) {
        this.name = name;
        this.number = number;
        this.program = program;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return number == channel.number && Objects.equals(name, channel.name) && Objects.equals(program, channel.program);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, program);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", program='" + program + '\'' +
                '}';
    }


}