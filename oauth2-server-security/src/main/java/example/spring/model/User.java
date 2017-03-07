package example.spring.model;

import java.util.HashMap;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class User {

    private Long id;

    private String name;

    private String email;

    private String externalId;

    private String externalUrl;

    public User() {

    }

    public User(AbstractAuthenticationToken principal) {
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication principalOauth = (OAuth2Authentication) principal;
            @SuppressWarnings("unchecked")
            HashMap<String, Object> details = (HashMap<String, Object>) principalOauth.getUserAuthentication().getDetails();
            this.name = details.get("name").toString();
            this.externalId = details.get("id").toString();
            this.externalUrl = (details.containsKey("url") ? details.get("url") : details.get("link")).toString();

        } else {
            this.name = principal.getName();
        }
    }

    public User(String name, String email, String externalId, String externalUrl) {
        this.name = name;
        this.email = email;
        this.externalId = externalId;
        this.externalUrl = externalUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

}
