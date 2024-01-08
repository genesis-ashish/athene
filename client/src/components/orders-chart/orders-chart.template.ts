import { html } from '@microsoft/fast-element';
import { OrdersChart } from './orders-chart';

export const ordersChartTemplate = html<OrdersChart>`
  <template>
    <zero-g2plot-chart type="pie" :config=${(x) => x.chartConfiguration}>
        <chart-datasource
        resourceName="ALL_TRADES"
        server-fields="SYMBOL QUANTITY"
        ></chart-datasource>
    </zero-g2plot-chart>
  </template>
`;
