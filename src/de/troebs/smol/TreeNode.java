package de.troebs.smol;

import java.util.LinkedList;
import java.util.List;

class TreeNode {
  final List<TreeNode> named;
  final List<TreeNode> typed;

  final String name;
  final List<Object> handlers;

  TreeNode(String name) {
    this.named = new LinkedList<>();
    this.typed = new LinkedList<>();

    this.name = name;
    this.handlers = new LinkedList<>();
  }

  TreeNode(String name, Object handler) {
    this(name);
    if (handler != null)
      this.handlers.add(handler);
  }
}
