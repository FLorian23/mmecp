package de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.impl;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.json.ChannelBean;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.Channel;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by benjamin on 24.08.14.
 */
public class ChannelImpl implements Channel, Serializable {

    private final ChannelBean channel;

    public ChannelImpl(ChannelBean channel) {
        this.channel = channel;
    }

    @Override
    public String getTitle() {
        return this.channel.getTitle();
    }

    @Override
    public void setTitle(String title) {
        this.channel.setTitle(title);
    }

    @Override
    public String getId() {
        return this.channel.getId();
    }

    @Override
    public void setId(String id) {
        this.channel.setId(id);
    }

    @Override
    public URL getUrl() {
        return this.channel.getUrl();
    }

    @Override
    public void setUrl(URL url) {
        this.channel.setUrl(url);
    }

    @Override
    public boolean isStandard() {
        return this.channel.isStandard();
    }

    @Override
    public void setStandard(boolean standard) {
        this.channel.setStandard(standard);
    }
}
