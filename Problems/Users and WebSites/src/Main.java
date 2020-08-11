abstract class BaseEntity {

    protected long id;
    protected long version;

    public BaseEntity() {
        this.id = id;
        this.version = version;
    }


    abstract long getId();

    abstract void setId(long id);

    abstract long getVersion();

    abstract void setVersion(long version);

}

class User extends BaseEntity {

    protected String name;

    public User(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
        super();
    }
    @Override
    public long getId() {
        return id;
    }
    @Override
    public void setId(long id) {
        this.id = id;
    }
    @Override
    public long getVersion() {
        return version;
    }
    @Override
    public void setVersion(long version) {
        this.version = version;
    }
}
class Visit extends BaseEntity {

    protected User user;

    protected WebSite site;

    public Visit(User user, WebSite site) {
        super();
        this.user = user;
        this.site = site;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public WebSite getSite() {
        return site;
    }

    public void setSite(WebSite site) {
        this.site = site;
    }
    public Visit() {
        super();
    }
    @Override
    public long getId() {
        return id;
    }
    @Override
    public void setId(long id) {
        this.id = id;
    }
    @Override
    public long getVersion() {
        return version;
    }
    @Override
    public void setVersion(long version) {
        this.version = version;
    }
}

class WebSite extends BaseEntity {


    protected String url;

    public WebSite(String url) {
        super();
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public WebSite() {
        super();
    }
    @Override
    public long getId() {
        return id;
    }
    @Override
    public void setId(long id) {
        this.id = id;
    }
    @Override
    public long getVersion() {
        return version;
    }
    @Override
    public void setVersion(long version) {
        this.version = version;
    }
}