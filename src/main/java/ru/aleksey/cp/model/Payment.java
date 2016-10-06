package ru.aleksey.cp.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Алексей on 29.09.2016.
 */

@Entity
@Table(name="PAYMENTS")
public class Payment {


    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="PAYMENTS_ID_SEQ", sequenceName="PAYMENTS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENTS_ID_SEQ")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }



    @Column(name = "PMT_TIME", insertable = false)
    @Type(type="date")
    private Date pmt_time;

    @Column(name = "SUMM")
    private Float summ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public int getAccount_id() {
//        return account_id;
//    }
//
//    public void setAccount_id(int account_id) {
//        this.account_id = account_id;
//    }

    public Date getPmt_time() {
        return pmt_time;
    }

    public void setPmt_time(Date pmt_time) {
        this.pmt_time = pmt_time;
    }

    public Float getSumm() {
        return summ;
    }

    public void setSumm(Float summ) {
        this.summ = summ;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
//                ", account_id=" + account_id +
                ", pmt_time='" + pmt_time + '\'' +
                ", summ=" + summ +
                '}';
    }
}
