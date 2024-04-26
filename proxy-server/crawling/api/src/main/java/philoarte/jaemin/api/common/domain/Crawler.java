package philoarte.jaemin.api.common.domain;

import lombok.Data;


public class Crawler {

    private String url;
    private String cssQuery;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    public static class Funding{
        private long tumblebuckId;

        private String category;

        private String title;

        private String address;

        public long getTumblebuckId() {
            return tumblebuckId;
        }

        public void setTumblebuckId(Long tumblebuckId) {
            this.tumblebuckId = tumblebuckId;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
