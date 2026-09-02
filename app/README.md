| Abordagem                     | Rotação de tela | Morte do processo  |
|-------------------------------|:---------------:|:------------------:|
| remember                      |  Não sobrevive  |   Não sobrevive    |
| ViewModel + mutableStateOf    |    Sobrevive    |   Não sobrevive    |
| ViewModel + StateFlow         |    Sobrevive    |   Não sobrevive    |
| ViewModel + SavedStateHandle  |    Sobrevive    |     Sobrevive      |

## Perguntas

1. Por que o ViewModel sozinho (etapas 2 e 3) não é suficiente para sobreviver à morte do processo, mesmo sobrevivendo à rotação de tela?

O ViewModel sobrevive à rotação porque a sua instância é retida pelo sistema durante a recriação da Activity.
Já a morte do processo encerra a aplicação inteira, apagando toda a memória, inclusive o ViewModel.
Como mutableStateOf e StateFlow guardam o valor só em RAM, sem nenhuma serialização, o dado se perde.

2. Qual a diferença prática entre usar mutableStateOf e StateFlow dentro do ViewModel nesta aplicação? Em algum momento essa diferença foi perceptível nos testes?

MutableStateOf é uma API do Compose, simples e direta, mas acoplada à recomposição.
StateFlow é mais genérica, observável fora do Compose e melhor para testes e composição com outras fontes assíncronas.
Nos testes de rotação e morte de processo, essa diferença não foi perceptível, já que ambas guardam o estado só na memória do ViewModel.

3. Se este placar precisasse ser salvo permanentemente (mesmo após o usuário fechar o app e abrir dias depois), qual das quatro abordagens ainda seria insuficiente, e o que seria necessário adicionar?

Todas seriam insuficientes, inclusive o SavedStateHandle, que só persiste durante o ciclo de vida do processo e
não sobrevive ao usuário fechar o app ou reiniciar o aparelho. Seria necessário gravar em disco, usando DataStore ou um banco local como Room,
salvando o placar a cada alteração e recarregando na inicialização do ViewModel.

4. Na sua opinião, qual abordagem você usaria em produção para este placar e por quê?

ViewModel + SavedStateHandle, por cobrir o cenário mais comum sem esforço extra:
o sistema mata o processo em segundo plano e o placar continua intacto ao voltar.
Se fosse necessária persistência permanente entre sessões, eu manteria essa base e adicionaria DataStore por trás do ViewModel.