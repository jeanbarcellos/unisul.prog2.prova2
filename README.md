_Repositório apenas para o armazenamento dos arquivos e trabalhos da faculdade_

# Avaliação 2 - Tópicos Avançados de Programação (Programação 2) - 4ª Fase - Unisul 2016/2

Prova 2 (Threds) da disciplina de Tópicos Avançados de Programação (Programação 2) - 4ª Fase - Unisul 2016/2

## Conteúdo

A prova consiste em:

- Consumir um arquivo txt (`conteudo/prova2.txt`) contendo a classificação de um vestibular
  - O conteúdo do arquivo contem cada candidado e uma linha, com os dados sepadados por `;` (CSV)
- Gerar um arquivo, no formado `SQL`, com os comandos para cadastrar o candidado e sua clasificação em um Banco de Dados.
- Gerar um segundo arquivo contendo uma lista de nomes comuns
  - Não pode haver nomes repetidos
  - A lista deve estar ordenada

## Resolução

- Utilizando `Buffer`

  - `src/jeanbarcellos`

- Utilizando `ArrayBlockingQueue`
  - `src/blockingqueue`

## Resultado / Feedback

- O trabalho recebeu nota `9` de `10` pelo professor.
- O desconto de `1` se deu porque utilizei um `ArrayList` ao invés de `TreeSet` no momento de montar a lista de candidatos.
  - Para não adicionar nomes repetidos eu primeiramente fiz uma verificação pra ver se o nome do candidado já estava contido na lista e depois adicionava o mesmo.
  - Enquanto o `TreeSet`, por ser uma especialização de `SortedTree`, já implementa uma coleção de objetos única e ordenada, não sendo necessária a implementção que fiz.
- O código já foi corrigido/atualizado, após feedback do professor
