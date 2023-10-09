import {html} from '@microsoft/fast-element';
import type {AdminPage} from './admin-page';

export const AdminPageTemplate = html<AdminPage>`
    <foundation-layout>
        <foundation-layout-region type="horizontal">
            <foundation-layout-item title="User Management" registration="bar">
                <user-management></user-management>
            </foundation-layout-item>
                <foundation-layout-item title="Profile Management">
                    <profile-management></profile-management>
                </foundation-layout-item>
        </foundation-layout-region>
    </foundation-layout>
`;
