package ru.elena.bobr.servlet.dto;

import java.util.UUID;

public class IncomingDtoAnother {
    UUID id;
    String nickname;
    UUID ownerId;

    public void setNiсkname(String nickname) {
        this.nickname = nickname;
    }
/* public IncomingDtoAnother(UUID id, String nickname, UUID ownerId) {
        this.id = id;
        this.nickname = nickname;
        this.ownerId = ownerId;
    }*/

 /*   public IncomingDtoAnother() {

    }*/

    public UUID getOwner() {
        return ownerId;
    }

  public void setOwner(UUID ownerid) {
      this.ownerId = ownerid;
 }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

  //  public UUID getOwnerId() {
  //      return ownerId;
   // }

  //  public void setOwnerId(UUID ownerId) {
    //    this.ownerId = ownerId;
  //  }

    public String getNickname() {
        return nickname;
    }


}


