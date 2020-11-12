UPDATE : 
Sobre o feedback anterior: 
-Modularização : Modularizamos o programa melhor, como sugerido. Criamos um pacote para as atividades e um pacote para as classes de execução principais de nosso programa (principal.java e manager.java), agora os pacotes estão com arquivos mais coerentes com sua função no código.

-Atividades : Mudamos a classe atividade, agora ela é uma classe abstrata, como requisitado. Adaptamos suas extensões para funcionarem dessa nova maneira.

-Relatórios : Implementamos todos os relatórios, apenas a parte de carga horária das disciplinas não funcionou corretamente e optamos por retirá-la, todas as demais funcionam como deveriam.

-Manager : Infelizmente não conseguimos reduzir muito a classe manager, em primeira instância, após o feedback, fizemos esforços e a reduzimos um pouco, entretanto, logo em seguida, mesmo separando o pacote que lida com os arquivos CSV, acabamos poluindo ela novamente e ficou difícil (devido a outros ajustes que eram necessários no momento) conseguirmos reduzí-la como gostaríamos à tempo. Pedimos desculpa por essa.

-Ordem alfabética: Tememos que em nosso código o professor Vitor continue em último (mas não menos importante). Brincadeiras a parte, assim como a redução da manager, acabamos deixando essa parte mais para o final e o tempo ficou curto demais.

-Output nos casos de erro: O nosso output nos casos de erro exibe também o nome da exception. (Ex. Saída esperada: Referência inválida : INF09331-2020/E. Nossa saída : custom_exceptions.InvallidReferenceException: Referencia invalida: INF09331-2020/E). Estamos cientes disso, mas não conseguimos fazer o ajuste necessário.
