package com.tlkj.cod.config.service;


import com.tlkj.cod.config.model.CodConfigChangeEvent;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public interface ConfigChangeListener {
  /**
   * Invoked when there is any config change for the namespace.
   * @param changeEvent the event for this change
   */
  public void onChange(CodConfigChangeEvent changeEvent);
}
