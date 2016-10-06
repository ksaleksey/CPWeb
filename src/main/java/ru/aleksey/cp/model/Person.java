package ru.aleksey.cp.model;

import javax.persistence.*;

/**
 * Created by Алексей on 01.10.2016.
 */
@Entity
@Table(name = "PERSONS")
public class Person {

    @Id
    @Column(name="ID")
    @SequenceGenerator(name="PERSONS_ID_SEQ", sequenceName="PERSONS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PERSONS_ID_SEQ")

    private int id;

    @Column(name="FAM")
    private String fam;

    @Column(name="IM")
    private String im;

    @Column(name="OTCH")
    private String otch;

    @Column(name="PASSPORT")
    private Long passport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFam() {
        return fam;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public Long getPassport() {
        return passport;
    }

    public void setPassport(Long passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fam='" + fam + '\'' +
                ", im='" + im + '\'' +
                ", otch='" + otch + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
