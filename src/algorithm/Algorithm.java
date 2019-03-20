package algorithm;

import problem.Problem;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private Problem problem;

    public Algorithm(Problem problem)
    {
        this.problem = problem;
    }

    public List<Integer> solve()
    {
        int[][] copy,
                matrix = problem.getData();
        int count, max, x, i, j;

        List<Integer> vertexCover = new ArrayList<>();
        int[] degree = new int[problem.getDim()],
                verticesMaxDegree = new int[problem.getDim()];
        int r;

        // Клонируем исходный массив
        copy = arrayClone(matrix);

        // Пока не удалены все ребра
        while(true)
        {
            int k = 0, flag = 0;

            // Подсчет чисел инцидентности вершин
            for(i = 0; i < problem.getDim(); i++)
            {
                count = 0;
                for(j = 0; j < problem.getDim(); j++)
                {
                    if(copy[i][j] == 1)
                    {
                        flag++;
                        count++;
                    }
                }
                degree[i] = count;
            }

            // Если нет инцидентных ребер, остановка цикла
            if(flag == 0) break;

            // Находим вершину с максимальным числом инцидентности
            max = findMaxDegree(degree);

            // Запоминаем вершину с максимальным числом инцидентности
            for(i = 0; i < problem.getDim(); i++)
            {
                if(max == degree[i]) verticesMaxDegree[k++] = i;
            }

            // Выбираем ту вершину, которая имеет хотя бы одно ребро, не покрытое другими вершинами,
            // среди тех, которые имеют максимальное число инцидентности
            for(i = 0; i < k; i++)
            {
                x = 0;
                for(j = 0; j < problem.getDim(); j++)
                {
                    if(copy[verticesMaxDegree[i]][j] == 1)
                    {
                        for(r = 0; r < k; r++)
                        {
                            if(j == verticesMaxDegree[r]) x++;
                        }
                    }
                }
                if(x < max) break;
            }

            if(i == k) i = 0;
            vertexCover.add(verticesMaxDegree[i]);

            // Удаляем инцидентные ребра выбранной вершины
            clearEdges(verticesMaxDegree[i], copy);
        }

        return vertexCover;
    }

    private int[][] arrayClone(int[][] src)
    {
        int [][] copy = new int[src.length][src.length];
        for(int i = 0; i < src.length; i++)
        {
            copy[i] = src[i].clone();
        }
        return copy;
    }

    private int findMaxDegree(int[] array)
    {
        int max = array[0];
        for(int item : array)
        {
            if(max < item) max = item;
        }
        return max;
    }

    private void clearEdges(int vertex, int[][] array)
    {
        for(int j = 0; j < array.length; j++)
        {
            if(array[vertex][j] == 1)
            {
                array[vertex][j] = 0;
                array[j][vertex] = 0;
            }
        }
    }

}
