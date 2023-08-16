package payload.Response;

import java.util.Date;

public class TokenRefreshResponse {

  private String accessToken;
  private String refreshToken;
  private String tokenType = "Bearer";
  private Date tokenExpirationDate;

  public TokenRefreshResponse(){}

  public TokenRefreshResponse(String accessToken, String refreshToken, Date tokenExpirationDate) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.tokenExpirationDate = tokenExpirationDate;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String token) {
    this.accessToken = token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public Date getTokenExpirationDate() { return tokenExpirationDate; }

  public void setTokenExpirationDate(Date tokenExpirationDate) { this.tokenExpirationDate = tokenExpirationDate; }
}
