/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Utils;

import java.io.Serializable;

/**
 *
 * @author DEll
 */
public class ErrorCheck implements Serializable{
    String userIdLength;
    String duplicateId;
    String passwordLength;
    String nameLength;
    String middleNameLength;
    String lastNameLength;
    String addressLength;
    String phoneNumber;
    String dateError;

    public ErrorCheck() {
    }

    public String getUserIdLength() {
        return userIdLength;
    }

    public void setUserIdLength(String userIdLength) {
        this.userIdLength = userIdLength;
    }

    public String getDuplicateId() {
        return duplicateId;
    }

    public void setDuplicateId(String duplicateId) {
        this.duplicateId = duplicateId;
    }

    public String getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(String passwordLength) {
        this.passwordLength = passwordLength;
    }

    public String getNameLength() {
        return nameLength;
    }

    public void setNameLength(String nameLength) {
        this.nameLength = nameLength;
    }

    public String getMiddleNameLength() {
        return middleNameLength;
    }

    public void setMiddleNameLength(String middleNameLength) {
        this.middleNameLength = middleNameLength;
    }

    public String getLastNameLength() {
        return lastNameLength;
    }

    public void setLastNameLength(String lastNameLength) {
        this.lastNameLength = lastNameLength;
    }

    public String getAddressLength() {
        return addressLength;
    }

    public void setAddressLength(String addressLength) {
        this.addressLength = addressLength;
    }
    
    

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateError() {
        return dateError;
    }

    public void setDateError(String dateError) {
        this.dateError = dateError;
    }
    
    public boolean checkContainNum(String s){
        char[] arr=s.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(Character.isDigit(arr[i])) return true;
        }
        return false;
    }
    
    public boolean checkValidPhone(String s){
        char[] arr = s.toCharArray();
        for(int i = 0;i<arr.length;i++){
            if(!Character.isDigit(arr[i])){
                return false;
            }
        }
        return true;
    }
    
}
