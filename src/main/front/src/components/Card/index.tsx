import React from "react";
import {CardViewModel} from "../../models/CardViewModel";
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardHeader from '@material-ui/core/CardHeader';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/DeleteOutline';
import EditIcon from '@material-ui/icons/Edit';
import LocationIcon from '@material-ui/icons/LocationOnOutlined';
import TimeIcon from '@material-ui/icons/AccessTime';

import "./Card.less"
import moment from "moment";

interface Props extends CardViewModel {
    onDelete: (cardId: number) => void
    onEdit: (card: CardViewModel) => void
}

const MyCard: React.FC<Props> = ({onDelete, onEdit, ...card}) => (
    <Card>
        <CardHeader
            action={
                <>
                    <IconButton aria-label="settings" onClick={() => onEdit(card)}>
                        <EditIcon fontSize="small"/>
                    </IconButton>
                    <IconButton aria-label="settings" onClick={() => onDelete(card.cardId)}>
                        <DeleteIcon fontSize="small"/>
                    </IconButton>
                </>
            }
            title={card.title}
            subheader={card.username}
        />
        <CardContent>
            {card.startDate || card.endDate ? (
                <div className="Card-field">
                    <TimeIcon fontSize="small"/>
                    <div className="Card-field-desc">
                        <div>{card.startDate ? `from: ${moment(card.startDate).format("DD.MM.YYYY")}` : ''}</div>
                        <div>{card.endDate ? `to: ${moment(card.endDate).format("DD.MM.YYYY")}` : ''}</div>
                    </div>
                </div>
            ) : null}
            {card.locationString ? (
                <div className="Card-field">
                    <LocationIcon fontSize="small"/>
                    <span className="Card-field-desc">{card.locationString}</span>
                </div>
             ) : null}
        </CardContent>
    </Card>
);

export default MyCard;