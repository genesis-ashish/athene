import {customElement, FASTElement} from '@microsoft/fast-element';
import {AdminPageTemplate as template} from './admin-page.template';
import {AdminPageStyles as styles} from './admin-page.styles';
import {logger} from '../../utils';
import {Users, } from '@genesislcap/foundation-entity-management';

const name = 'admin-page';
Users;
@customElement({
    name,
    template,
    styles,
})
export class AdminPage extends FASTElement {
    public connectedCallback() {
        super.connectedCallback();
        logger.debug(`${name} is now connected to the DOM`);
    }
}
