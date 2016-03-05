package com.bilig.ugted.domain;

public class UpdateInfo
{
        private int version;
        private String description;
        private String url;
        
        public int getVersion()
        {
                return version;
        }
        public void setVersion(int version)
        {
                this.version = version;
        }
        public String getDescription()
        {
                return description;
        }
        public void setDescription(String description)
        {
                this.description = description;
        }
        public String getUrl()
        {
                return url;
        }
        public void setUrl(String url)
        {
                this.url = url;
        }
        
}
