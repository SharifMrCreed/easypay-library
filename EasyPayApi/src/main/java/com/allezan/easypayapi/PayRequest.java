
package com.allezan.easypayapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class PayRequest implements Parcelable {

    @Expose
    private String action;
    @Expose
    private int amount;
    @Expose
    private String currency;
    @Expose
    private String password;
    @Expose
    private String phone;
    @Expose
    private String reason;
    @Expose
    private String reference;
    @Expose
    private String username;

    public PayRequest() {
    }

    protected PayRequest(Parcel in) {
        action = in.readString();
        amount = in.readInt();
        currency = in.readString();
        password = in.readString();
        phone = in.readString();
        reason = in.readString();
        reference = in.readString();
        username = in.readString();
    }

    public static final Creator<PayRequest> CREATOR = new Creator<PayRequest>() {
        @Override
        public PayRequest createFromParcel(Parcel in) {
            return new PayRequest(in);
        }

        @Override
        public PayRequest[] newArray(int size) {
            return new PayRequest[size];
        }
    };

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(action);
        dest.writeInt(amount);
        dest.writeString(currency);
        dest.writeString(password);
        dest.writeString(phone);
        dest.writeString(reason);
        dest.writeString(reference);
        dest.writeString(username);
    }
}
