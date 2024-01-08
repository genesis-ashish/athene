import {html} from '@microsoft/fast-element';
import type {Home} from './home';
import {OrdersChart} from "../../components/orders-chart/orders-chart";
OrdersChart
export const HomeTemplate = html<Home>`
    <foundation-layout>
        <foundation-layout-region type="horizontal">
            <foundation-layout-region type="vertical">
                <foundation-layout-item title="All Data" registration="bar" closable>
                    <entity-management
                            resourceName="ALL_TRADES"
                            createEvent="EVENT_TRADE_INSERT"
                            updateEvent="EVENT_TRADE_MODIFY"
                    ></entity-management>
                </foundation-layout-item>
                <foundation-layout-item title="All Maturity">
                    <entity-management resourceName="ALL_MATURITY">
                    </entity-management>
                </foundation-layout-item>
            </foundation-layout-region>
            
            <foundation-layout-region type="vertical">
                <foundation-layout-item title="Positions data">
                    <entity-management
                            resourceName="ALL_POSITIONS"
                    ></entity-management>
                </foundation-layout-item>
                <foundation-layout-item title="Symbol Chart">
                    <orders-chart></orders-chart>
                    <!--<entity-management
                            resourceName="ALL_SIDES"
                    ></entity-management>-->
                </foundation-layout-item>
            </foundation-layout-region>
        </foundation-layout-region>
    </foundation-layout>
`;
