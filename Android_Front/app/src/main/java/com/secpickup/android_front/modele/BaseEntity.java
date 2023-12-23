
package com.secpickup.android_front.modele;



import java.util.Date;


public class BaseEntity {


    private Long id;

    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Date updatedAt;
}
