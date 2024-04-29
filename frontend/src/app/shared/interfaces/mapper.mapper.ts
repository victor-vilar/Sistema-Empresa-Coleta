import { Observable } from "rxjs";

/**
 * Converte os itens que vinheram da API, para o formato de exibição no frontend.
 * Por exemplo na API o objeto vem com dados que eu não gostaria de exibir ou gostaria que esses dados
 * fossem exibidos de maneira diferente.
 *
 */
export interface Mapper {

  mapItens(list:any[]):any[];
}
