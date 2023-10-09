import {html} from '@microsoft/fast-element';
import type {Home} from './home';

export const HomeTemplate = html<Home>`
    <foundation-layout>
        <foundation-layout-region type="horizontal">
            <foundation-layout-region type="vertical">
                <foundation-layout-item title="All Data" registration="bar" closable>
                    <entity-management
                            resourceName="ALL_TRADES"
                            createEvent="EVENT_TRADE_INSERT"
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
                <foundation-layout-item title="Side data">
                    <entity-management
                            resourceName="ALL_SIDES"
                    ></entity-management>
                </foundation-layout-item>
            </foundation-layout-region>
        </foundation-layout-region>
    </foundation-layout>
`;
