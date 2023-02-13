package ru.swat1x.pmjail.management;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class JailerModel {

    String owner;
    String admin;
    long start;
    long end;

}
