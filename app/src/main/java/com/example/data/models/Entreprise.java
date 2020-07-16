package com.example.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entreprise implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("ville")
    @Expose
    private String ville;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("tel")
    @Expose
    private String tel;
    @SerializedName("codePostal")
    @Expose
    private Integer codePostal;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("servi")
    @Expose
    private Integer servi;
    @SerializedName("dernier")
    @Expose
    private Integer dernier;

    protected Entreprise(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nom = in.readString();
        logo = in.readString();
        ville = in.readString();
        adresse = in.readString();
        email = in.readString();
        tel = in.readString();
        if (in.readByte() == 0) {
            codePostal = null;
        } else {
            codePostal = in.readInt();
        }
        lat = in.readDouble();
        lng = in.readDouble();
        if (in.readByte() == 0) {
            servi = null;
        } else {
            servi = in.readInt();
        }
        if (in.readByte() == 0) {
            dernier = null;
        } else {
            dernier = in.readInt();
        }

    }

    public static final Creator<Entreprise> CREATOR = new Creator<Entreprise>() {
        @Override
        public Entreprise createFromParcel(Parcel in) {
            return new Entreprise(in);
        }

        @Override
        public Entreprise[] newArray(int size) {
            return new Entreprise[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    public Integer getServi() {
        return servi;
    }

    public void setServi(Integer servi) {
        this.servi = servi;
    }

    public Integer getDernier() {
        return dernier;
    }

    public void setDernier(Integer dernier) {
        this.dernier = dernier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(nom);
        dest.writeString(logo);
        dest.writeString(ville);
        dest.writeString(adresse);
        dest.writeString(email);
        dest.writeString(tel);
        if (codePostal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(codePostal);
        }
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        if (servi == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(servi);
        }
        if (dernier == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(dernier);
        }
    }
}
