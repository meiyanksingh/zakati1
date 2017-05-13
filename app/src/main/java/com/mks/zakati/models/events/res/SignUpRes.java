package com.mks.zakati.models.events.res;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mayank on 10/5/17.
 */

public class SignUpRes implements Parcelable{


    /**
     * id : 65
     * email : fdgdfgfsdfg.932sadjhg@gmail.com
     * username : dfgdsdfsdfgfdg
     * access_token : dddb2a4cfbed9150b54d97e03e67adaa
     * userid : 105
     * type : 1
     * fname : tuneer
     * lname : malik
     * city : sdsdasd
     * country : dasasdsa
     * telephone : 5656565
     * about_me : jkhjgjhgh
     * makes_smile : 0
     * profile_pic : http://techyug.in/zakati-23/images/profile/dummy.jpg
     * dob : 2016-12-12
     * deleted_at : null
     * org_name : null
     * full_address : null
     * charity_cert : dummy.jpg
     * proof_of_inco : dummy.jpg
     * brief_profile : dummy.jpg
     * govt_id : dummy.jpg
     * bank_swift : null
     * bank_iban : null
     * vat_no : null
     * name_of_account : null
     * iacad : null
     */

    private String id;
    private String email;
    private String username;
    private String access_token;
    private String userid;
    private String type;
    private String fname;
    private String lname;
    private String city;
    private String country;
    private String telephone;
    private String about_me;
    private String makes_smile;
    private String profile_pic;
    private String dob;
    private Object deleted_at;
    private Object org_name;
    private Object full_address;
    private String charity_cert;
    private String proof_of_inco;
    private String brief_profile;
    private String govt_id;
    private Object bank_swift;
    private Object bank_iban;
    private Object vat_no;
    private Object name_of_account;
    private Object iacad;

    public SignUpRes(){}

    protected SignUpRes(Parcel in) {
        id = in.readString();
        email = in.readString();
        username = in.readString();
        access_token = in.readString();
        userid = in.readString();
        type = in.readString();
        fname = in.readString();
        lname = in.readString();
        city = in.readString();
        country = in.readString();
        telephone = in.readString();
        about_me = in.readString();
        makes_smile = in.readString();
        profile_pic = in.readString();
        dob = in.readString();
        charity_cert = in.readString();
        proof_of_inco = in.readString();
        brief_profile = in.readString();
        govt_id = in.readString();
    }

    public static final Creator<SignUpRes> CREATOR = new Creator<SignUpRes>() {
        @Override
        public SignUpRes createFromParcel(Parcel in) {
            return new SignUpRes(in);
        }

        @Override
        public SignUpRes[] newArray(int size) {
            return new SignUpRes[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getMakes_smile() {
        return makes_smile;
    }

    public void setMakes_smile(String makes_smile) {
        this.makes_smile = makes_smile;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Object getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Object deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Object getOrg_name() {
        return org_name;
    }

    public void setOrg_name(Object org_name) {
        this.org_name = org_name;
    }

    public Object getFull_address() {
        return full_address;
    }

    public void setFull_address(Object full_address) {
        this.full_address = full_address;
    }

    public String getCharity_cert() {
        return charity_cert;
    }

    public void setCharity_cert(String charity_cert) {
        this.charity_cert = charity_cert;
    }

    public String getProof_of_inco() {
        return proof_of_inco;
    }

    public void setProof_of_inco(String proof_of_inco) {
        this.proof_of_inco = proof_of_inco;
    }

    public String getBrief_profile() {
        return brief_profile;
    }

    public void setBrief_profile(String brief_profile) {
        this.brief_profile = brief_profile;
    }

    public String getGovt_id() {
        return govt_id;
    }

    public void setGovt_id(String govt_id) {
        this.govt_id = govt_id;
    }

    public Object getBank_swift() {
        return bank_swift;
    }

    public void setBank_swift(Object bank_swift) {
        this.bank_swift = bank_swift;
    }

    public Object getBank_iban() {
        return bank_iban;
    }

    public void setBank_iban(Object bank_iban) {
        this.bank_iban = bank_iban;
    }

    public Object getVat_no() {
        return vat_no;
    }

    public void setVat_no(Object vat_no) {
        this.vat_no = vat_no;
    }

    public Object getName_of_account() {
        return name_of_account;
    }

    public void setName_of_account(Object name_of_account) {
        this.name_of_account = name_of_account;
    }

    public Object getIacad() {
        return iacad;
    }

    public void setIacad(Object iacad) {
        this.iacad = iacad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(access_token);
        dest.writeString(userid);
        dest.writeString(type);
        dest.writeString(fname);
        dest.writeString(lname);
        dest.writeString(city);
        dest.writeString(country);
        dest.writeString(telephone);
        dest.writeString(about_me);
        dest.writeString(makes_smile);
        dest.writeString(profile_pic);
        dest.writeString(dob);
        dest.writeString(charity_cert);
        dest.writeString(proof_of_inco);
        dest.writeString(brief_profile);
        dest.writeString(govt_id);
    }
}
