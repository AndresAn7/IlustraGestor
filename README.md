# IlustraGestor

## Proceso que escogí

La facturación de los clientes. Cada cliente tiene sus encargos entregados y hay que
generar una factura por cliente. Como la factura de uno no depende de la de otro, se
pueden hacer al mismo tiempo en vez de una por una. Además, en la vida real una factura
puede tardar, así que paralelizarlo ayuda.

## Tipo de concurrencia: ExecutorService

Creé un pool de 4 hilos y le mandamos una tarea por cada cliente. Cuando un hilo
termina, agarra la siguiente tarea.

Lo escogí porque primero, hay muchas tareas y el pool reutiliza los hilos en vez de crear uno nuevo cada vez.
Segundo, cada tarea devuelve una factura, y con Runnable eso no se puede.
Tercero, la tabla de la clase lo recomienda para muchas tareas.

Lo malo es que hay que cerrar el pool, por eso usamos `shutdown()` en un `finally`.

## Sincronización

Todos los hilos anotan sus facturas en `RegistroFacturacion`. Como lo usan a la vez, sus
métodos son `synchronized`; si no, se podían perder sumas o repetirse números de factura.

## Resultado

En la sección 8 de la consola, la versión normal la hace solo el hilo `main` y la del
pool la hacen los hilos `pool-1-thread-1` a `4`, mezclados. Tiempos: 2444 ms normal
contra 644 ms con el pool. Los totales dan igual en las dos (2392200.0).