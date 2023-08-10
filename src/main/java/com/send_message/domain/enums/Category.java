package com.send_message.domain.enums;

public enum Category {

        Sports("sports"),
        Finance("finance"),
        Films("films");

        private final String value;

        Category(String value) {
            this.value = value;
        }

       public String getValue() {
            return value;
       }

}
