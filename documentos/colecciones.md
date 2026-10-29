
| Necesidad                  | Coleccion | Por que?                                                                                                                                                                        |
| -------------------------- | --------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Servicios                  | Map       | Porque solo puede haber un solo servicio por codigo, y necesitamos poder buscarlo por el mismo, con una estructura de Map<String,Servicio>.                                     |
| Pasajes                    | List      | Porque solo se necesita guardar pasajes que son unicos basados en su numero de asiento, y se necesita buscarlos por ese mismo numero, con una estructura de ArrayList\<Pasaje>. |
| Lista de espera            | Queue     | Porque se necesita una estructura de fila para asegurarnos de que el primer pasajero que pida se lleve el pasaje, con una estructura de ArrayDeque\<Pasajero>.                  |
| Recaudado por cada empresa | Map       | Necesitamos especificar un numero por cada empresa, con una estructura de Map<String,Double>                                                                                    |
