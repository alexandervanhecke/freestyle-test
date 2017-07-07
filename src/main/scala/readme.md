Raúl Raja Martínez @raulraja Jul 06 23:35

@alexandervanhecke I see what's going on.
Do you have the `-Ypartial-unification` flag in your compiler settings?
This is unrelated to Freestyle but the issue I'm seeing is that it can't properly infer the type params for the arguments in the traverse call.
You can use one of the compiler versions that support `-Ypartial-unification` and leave the code as is or explicitly tell traverse what the type arguments are

Since traverse is:

```
def traverse[G[_]: Applicative, A, B](fa: F[A])(f: A => G[B]): G[F[B]]
```

Without partial unification you'd have to invoke it like `traverse[FreeS[F, ?], Int, Int]` assuming you have kind projector enabled in your project.

assigning the call to a typed val like val: `FreeS[F, List[Int]] = Traverse[List[...` may also help inference.

