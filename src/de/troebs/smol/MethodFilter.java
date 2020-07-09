package de.troebs.smol;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

class MethodFilter implements Comparator<Method> {
  private static final PriorityGetter GET = new GetGetter();
  private static final PriorityGetter POST = new PostGetter();
  private static final PriorityGetter PUT = new PutGetter();
  private static final PriorityGetter PATCH = new PatchGetter();
  private static final PriorityGetter DELETE = new DeleteGetter();

  private final Class verb;
  private final PriorityGetter getter;


  MethodFilter(String method) {
    switch (method) {
      case "GET":
        this.verb = Get.class;
        this.getter = GET;
        break;
      case "POST":
        this.verb = Post.class;
        this.getter = POST;
        break;
      case "PUT":
        this.verb = Put.class;
        this.getter = PUT;
        break;
      case "PATCH":
        this.verb = Patch.class;
        this.getter = PATCH;
        break;
      case "DELETE":
        this.verb = Delete.class;
        this.getter = DELETE;
        break;
      default:
        this.verb = null;
        this.getter = null;
        // break;
    }
  }


  public Method[] getEligibleMethods(Object handler) {
    if (this.verb != null) {
      final Method[] methods = handler.getClass().getMethods();

      int i = 0;
      for (Method method : methods)
        if (method.isAnnotationPresent(this.verb))
          i += 1;

      final Method[] result = new Method[i];

      i = 0;
      for (Method method : methods)
        if (method.isAnnotationPresent(this.verb))
          result[i++] = method;

      Arrays.sort(result, this);
      return result;
    }

    return new Method[0];
  }


  @Override
  public int compare(Method a, Method b) {
    return Integer.compare(this.getter.getPriority(b), this.getter.getPriority(a));
  }

  private static interface PriorityGetter {
    public int getPriority(Method method);
  }

  private static class GetGetter implements PriorityGetter {
    @Override
    public int getPriority(Method method) {
      return method.getAnnotation(Get.class).priority();
    }
  }

  private static class PostGetter implements PriorityGetter {
    @Override
    public int getPriority(Method method) {
      return method.getAnnotation(Post.class).priority();
    }
  }

  private static class PutGetter implements PriorityGetter {
    @Override
    public int getPriority(Method method) {
      return method.getAnnotation(Put.class).priority();
    }
  }

  private static class PatchGetter implements PriorityGetter {
    @Override
    public int getPriority(Method method) {
      return method.getAnnotation(Patch.class).priority();
    }
  }

  private static class DeleteGetter implements PriorityGetter {
    @Override
    public int getPriority(Method method) {
      return method.getAnnotation(Delete.class).priority();
    }
  }
}
