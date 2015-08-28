package com.thoughtworks.constant;

import static com.thoughtworks.constant.MetaConstants.*;

public enum FileNameConstants {
    VEHICLES,
    VEHICLES_COUNT;

    public String columnSeparator() {
        return this.toString() + "." + COLUMN_SEPARATOR;
    }

    public String path() {
        return this.toString() + "." + PATH;
    }

    public String distributedCacheLocation() {
        return "./" + this.toString();
    }

    public String referenceDate() {
        return this.toString() + "." + REFERENCE_DATE;
    }

    public String referenceDateFormat() {
        return this.toString() + "." + REFERENCE_DATE_FORMAT;
    }

    public String dateFormat() {
        return this.toString() + "." + DATE_FORMAT;
    }
}
