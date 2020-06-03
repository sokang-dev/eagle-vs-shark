package model;

import model.Enums.StatusType;

import java.util.Objects;

public class Status {

    private StatusType type;
    private int duration;

    public Status(StatusType type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    public StatusType getType() {
        return this.type;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) return false;

        Status status = (Status) obj;
        return this.type == status.getType();
    }
}
