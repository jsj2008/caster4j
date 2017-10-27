package caster.demo.code;

public interface Function<R, A> {
    public R callback(A... args);
}
