package az.gigroup.edupo.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Active {
    ACTIVE(1),
    DEACTIVE(0);

    public final int value;
}
