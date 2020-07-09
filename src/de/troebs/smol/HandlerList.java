package de.troebs.smol;

import java.util.LinkedList;
import java.util.List;

class HandlerList extends LinkedList<HandlerList.Wrapper> {
  public final LinkedList<NameValuePair> values;

  public HandlerList() {
    this.values = new LinkedList<>();
  }

  public void pushValue(String name, String value) {
    this.values.add(new NameValuePair(name, value));
  }

  public void popValue() {
    this.values.removeLast();
  }

  public void addHandler(Object handler) {
    this.add(new Wrapper(handler, new LinkedList<>(this.values)));
  }

  public void addHandler(TreeNode node) {
    node.handlers.forEach(this::addHandler);
  }

  class NameValuePair {
    public final String name;
    public final String value;

    NameValuePair(String name, String value) {
      this.name = name;
      this.value = value;
    }
  }

  class Wrapper {
    public final Object handler;
    public final ParameterMap values;

    public Wrapper(Object handler, List<NameValuePair> values) {
      this.handler = handler;
      this.values = new ParameterMap(values);
    }
  }
}
